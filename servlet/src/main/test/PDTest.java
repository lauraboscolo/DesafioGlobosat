import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import br.com.dextra.WEKA.PD;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;


public class PDTest {

	@Test(expected = FileNotFoundException.class) 
	public void testPD() throws FileNotFoundException {
		PD pdT = new PD("qwertyuiop");
	}

	@Test
	public void testTreinar() {
		try {
			PD pdT = new PD("arffTest.arff");
			pdT.treinar();
		} catch (FileNotFoundException e) {
			fail("Lançou excessao de arquivo inexistente");
		} catch (Exception e) {
			fail("falha na criação do classificador");
		}
		
	}

	@Test
	public void testTreinarIntDoubleDoubleString() {
		fail("Not yet implemented");
	}

}
