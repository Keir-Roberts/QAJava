package main;

import static org.junit.Assert.assertEquals;
import java.util.Random;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(MockitoJUnitRunner.class)
public class testRPS {

@Mock
private Random rand;

@InjectMocks
private rpsThrow mockRPS;

@Test
public void testRockLose() {
	Mockito.when(this.rand.nextInt(3)).thenReturn(1);
	assertEquals("Should lose", "The opponent played PAPER so you LOSE", this.mockRPS.rpsGame(rps.ROCK));
}

@Test
public void testPaperWin() {
	Mockito.when(this.rand.nextInt(3)).thenReturn(2);
	assertEquals("Should Win", "The opponent played ROCK so you WIN", this.mockRPS.rpsGame(rps.PAPER));
}
@Test
public void testScissors() {
	Mockito.when(this.rand.nextInt(3)).thenReturn(0);
	assertEquals("Should Draw", "The opponent played SCISSORS so you DRAW", this.mockRPS.rpsGame(rps.SCISSORS));
}
}
