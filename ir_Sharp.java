    public static class Sharp {

        private static final int FREQUENCY = 38000; // T = 26.315 us
        private static final int BIT_MARK = 9;
        private static final int ONE_SPACE = 69;
        private static final int ZERO_SPACE = 30;
        private static final int DELAY = 46;

        private static final int INVERSE_MASK = 0x3FF;
        private static final int TOP_BIT = 0x4000;


        private static final IrCommandBuilder.SequenceDefinition SEQUENCE_DEFINITION = simpleSequence(BIT_MARK, ONE_SPACE, BIT_MARK, ZERO_SPACE);

        public static IrCommand buildSharp(int bitCount, int data) {
            return irCommandBuilder(FREQUENCY)
                    .sequence(SEQUENCE_DEFINITION, TOP_BIT, bitCount, data)
                    .pair(BIT_MARK, ZERO_SPACE)
                    .delay(DELAY)

                    .sequence(SEQUENCE_DEFINITION, TOP_BIT, bitCount, data ^ INVERSE_MASK)
                    .pair(BIT_MARK, ZERO_SPACE)
                    .delay(DELAY)
                    .build();
        }
    }

^^ Original Copy V1


#include "IRremote.h"
#include "IRremoteInt.h"

//==============================================================================
//                       SSSS  H   H   AAA   RRRR   PPPP
//                      S      H   H  A   A  R   R  P   P
//                       SSS   HHHHH  AAAAA  RRRR   PPPP
//                          S  H   H  A   A  R  R   P
//                      SSSS   H   H  A   A  R   R  P
//==============================================================================

// Sharp and DISH support by Todd Treece: http://unionbridge.org/design/ircommand
//
// The send function has the necessary repeat built in because of the need to
// invert the signal.
//
// Sharp protocol documentation:
//   http://www.sbprojects.com/knowledge/ir/sharp.htm
//
// Here is the LIRC file I found that seems to match the remote codes from the
// oscilloscope:
//   Sharp LCD TV:
//   http://lirc.sourceforge.net/remotes/sharp/GA538WJSA

#define SHARP_BITS             15
#define SHARP_BIT_MARK        245
#define SHARP_ONE_SPACE      1805
#define SHARP_ZERO_SPACE      795
#define SHARP_GAP          600000
#define SHARP_RPT_SPACE      3000

#define SHARP_TOGGLE_MASK  0x3FF

//+=============================================================================
#if SEND_SHARP
void  IRsend::sendSharpRaw (unsigned long data,  int nbits)
{
	enableIROut(38);

	// Sending codes in bursts of 3 (normal, inverted, normal) makes transmission
	// much more reliable. That's the exact behaviour of CD-S6470 remote control.
	for (int n = 0;  n < 3;  n++) {
		for (unsigned long  mask = 1UL << (nbits - 1);  mask;  mask >>= 1) {
			if (data & mask) {
				mark(SHARP_BIT_MARK);
				space(SHARP_ONE_SPACE);
			} else {
				mark(SHARP_BIT_MARK);
				space(SHARP_ZERO_SPACE);
			}
		}

		mark(SHARP_BIT_MARK);
		space(SHARP_ZERO_SPACE);
		delay(40);

		data = data ^ SHARP_TOGGLE_MASK;
	}
}
#endif

//+=============================================================================
// Sharp send compatible with data obtained through decodeSharp()
//                                                  ^^^^^^^^^^^^^ FUNCTION MISSING!
//
#if SEND_SHARP
void  IRsend::sendSharp (unsigned int address,  unsigned int command)
{
	sendSharpRaw((address << 10) | (command << 2) | 2, SHARP_BITS);
}
#endif


^^  another origional.. in format?




 



