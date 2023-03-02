package com.demo;
import com.demo.Wallet.WalletService;
import com.demo.Wallet.WalletException;
import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalletApplicationTests {

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private WalletService walletService;
	@Test
	void registerwalletTest() throws WalletException {
		// creating a new wallet
		WalletDto wallOne = new WalletDto(1, "Pranavya", 45000.0, "xyz@gmail.com", 256);
		WalletDto wallet = walletService.registerWallet(wallOne);

	}

	@Test
	void getWalletByIdTest() throws WalletException {
		// get wallet by Id
		assertThrows(WalletException.class,()->this.walletService.getWalletById(100));

	}

	@Test
	void UpdateWalletTest() throws WalletException {
		// updating a wallet
		WalletDto walletDto = walletRepository.createWallet(new WalletDto(1, "Navya", 30000.0, "xyz@gmail.com", 256));
	    WalletDto updatewalletDto = walletRepository.getWalletById(1);
	    assertEquals("Pranav", updatewalletDto.getName());
	}

	@Test
	void deleteWalletByIdThrowsExceptionTest() throws WalletException{
        // deleting a wallet
		assertThrows(WalletException.class,()->this.walletService.getWalletById(1));
	}


}


