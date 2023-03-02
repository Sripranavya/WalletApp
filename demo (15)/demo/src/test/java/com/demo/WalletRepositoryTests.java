package com.demo;

import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WalletRepositoryTests {
    // autowiring WalletRepository
    @Autowired
    WalletRepository walletRepository;

    @BeforeEach
    public void start()
    {
        //initialise wallet
        walletRepository.createWallet(new WalletDto(1,"Pranavya",30000.0,"xyz@gmail.com",256));
    }
    @Test
    public void getWalletByIdTest()
    {
        //get wallet
        WalletDto walletDto = walletRepository.getWalletById(1);
    }

    @Test
    public void updateWallet()
    {
        //update wallet
        WalletDto newDto= walletRepository.createWallet(new WalletDto(2,"Navya",30000.0,"xyz@gmail.com",356));
        WalletDto walletDto = walletRepository.updateWallet(newDto);
    }

    @Test
    public void deleteWalletTest()
    {
        //delete wallet
        WalletDto deletedWallet = walletRepository.deleteWalletById(1);
        assertEquals("Pranavya",deletedWallet.getName());
    }
}
