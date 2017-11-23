package test

import main.Card
import main.Rank
import main.Suit

class CardTest extends GroovyTestCase {

    private Card A;
    private Card B;
    private Card C;
    private Card D;
    private Card E;

    void setUp() {
        super.setUp()
        this.A = new Card(Suit.SPADES, Rank.ACE);
        this.B = new Card(Suit.HEARTS, Rank.ACE);
        this.C = new Card(Suit.CLUBS, Rank.ACE);
        this.D = new Card(Suit.DIAMONDS, Rank.ACE);
        this.E = new Card(Suit.SPADES, Rank.ACE);
    }

    void testCompareTo() {
        assert this.A.compareTo(this.E) == 0;
        assert this.A.compareTo(this.B) > 0;
        assert this.A.compareTo(this.C) > 0;
        assert this.A.compareTo(this.D) > 0;
        assert this.B.compareTo(this.C) > 0;
        assert this.C.compareTo(this.D) > 0;
    }
}
