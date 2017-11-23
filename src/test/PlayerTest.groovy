package test

import main.Card
import main.HandsOrder
import main.Player
import main.Rank
import main.Suit

class PlayerTest extends GroovyTestCase {

    private Card A
    private Card B
    private Card C
    private Card D
    private Card E
    private Card F
    private Card G
    private Card H
    private Player player1
    private Player player2
    private String notSorted
    private String sorted
    private String sortedSix

    void setUp() {
        super.setUp()
        this.A = new Card(Suit.SPADES, Rank.ACE)
        this.B = new Card(Suit.HEARTS, Rank.ACE)
        this.C = new Card(Suit.CLUBS, Rank.ACE)
        this.D = new Card(Suit.DIAMONDS, Rank.ACE)
        this.E = new Card(Suit.HEARTS, Rank.QUEEN)
        this.F = new Card(Suit.SPADES, Rank.QUEEN)
        this.G = new Card(Suit.HEARTS, Rank.QUEEN)
        this.H = new Card(Suit.HEARTS, Rank.QUEEN)

        this.player1 = new Player("Test Player 1")

        this.player1.receiveCard(this.A)
        this.player1.receiveCard(this.B)
        this.player1.receiveCard(this.C)
        this.player1.receiveCard(this.E)
        this.player1.receiveCard(this.D)

        this.player2 = new Player("Test Player 2")

        this.player2.receiveCard(this.E)
        this.player2.receiveCard(this.F)
        this.player2.receiveCard(this.G)
        this.player2.receiveCard(this.H)
        this.player2.receiveCard(this.A)

        this.notSorted = "Test Player 1: ACE of SPADES, ACE of HEARTS, ACE of CLUBS, QUEEN of HEARTS, ACE of DIAMONDS"
        this.sorted = "Test Player 1: QUEEN of HEARTS, ACE of DIAMONDS, ACE of CLUBS, ACE of HEARTS, ACE of SPADES"
        this.sortedSix = "Test Player 1: QUEEN of HEARTS, QUEEN of SPADES, ACE of DIAMONDS, ACE of CLUBS, " +
                "ACE of HEARTS, ACE of SPADES"

    }

    void testReceiveCard() {
        assert this.player1.getHand().size() == 5
        this.player1.receiveCard(this.F)
        assert this.player1.getHand().size() == 6
    }

    void testClearHand() {
        assert !this.player1.getHand().isEmpty()
        this.player1.clearHand()
        assert this.player1.getHand().isEmpty()
    }

    void testSortHand() {
        assert this.player1.toString() == this.notSorted
        this.player1.sortHand()
        assert this.player1.toString() == this.sorted
    }

    void testSortExtraCard() {
        assert this.player1.toString() == this.notSorted
        this.player1.sortHand()
        assert this.player1.toString() == this.sorted
        this.player1.receiveCard(this.F)
        assert this.player1.toString() == this.sorted + ", QUEEN of SPADES"
        this.player1.sortHand()
        assert this.player1.toString() == this.sortedSix
    }

    void testToString() {
        assert this.player1.toString() == this.notSorted
    }

    void testCalculateHandOrder() {
        this.player1.sortHand()
        this.player2.sortHand()
        this.player1.calculateHandOrder()
        this.player2.calculateHandOrder()
        assert this.player1.getOrder() == HandsOrder.FOURKIND
        assert this.player2.getOrder() == HandsOrder.FOURKIND
    }

    void testCompareTo() {
        this.player1.sortHand()
        this.player2.sortHand()
        this.player1.calculateHandOrder()
        this.player2.calculateHandOrder()
        assert this.player1.compareTo(this.player2) == 1
    }
}
