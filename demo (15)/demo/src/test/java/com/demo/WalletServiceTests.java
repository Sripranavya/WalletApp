package com.demo;

import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletException;
import com.demo.Wallet.WalletJpaRepository;
import com.demo.Wallet.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@SpringBootTest
public class WalletServiceTests {

    //autowiring walletService
    @Autowired
    private WalletService walletService;

    //mocking walletRepository
    @MockBean
    private WalletJpaRepository walletJpaRepository;
    @Test
    public void testServiceWithOutActualRepository() throws WalletException
    {
        given(this.walletJpaRepository.getWalletById(1))
                .willReturn(new WalletDto(1,"Pranavya", 30000.0,"xyz@gmail.com",256));
        assertEquals("Pranavya",walletService.getWalletById(1).getName());
    }

    @Test
    public void testGetWalletThrowsExceptionTest() throws WalletException
    {

        given(this.walletJpaRepository.getWalletById(200))
                .willReturn(null);
        assertThrows(WalletException.class,()->walletService.getWalletById(200));
    }
    @Test
    public void testUpdateWalletExceptionTest() throws WalletException
    {
          WalletDto walletDto= new WalletDto(2,"Navya",24000.0,"abc@gmail.com",756);
          given(this.walletJpaRepository.updateWallet(walletDto))
                  .willReturn(new WalletDto(2,"Navya",24000.0,"abc@gmail.com",456));
          assertEquals(456,walletService.getWalletById(2).getPwd());
    }

    @Test
    public void testDeleteWalletExceptionTest() throws WalletException
    {
        given(this.walletJpaRepository.deleteWalletById(1))
                .willReturn(new WalletDto(1,"Pranavya",30000.0,"xyz@gmail.com",256));
        assertThrows(WalletException.class,()->walletService.deleteWalletById(1));
    }
    @Test
    public void addFundsToWalletTest() throws WalletException{

        given(this.walletJpaRepository.getWalletById(2))
                .willReturn((new WalletDto(2, "Navya", 24000.0,"abc@gmail.com",456)));
        given(this.walletJpaRepository.getWalletById(4))
                .willReturn((new WalletDto(4, "Sri", 30000.0,"bgy@gmail.com",854)));

        Double newBalance=this.walletService.addFundsToWalletById(2,450.0);
        assertEquals(Optional.of(24450.0),newBalance);
    }

    @Test
    public void withdrawFundsFromWalletTest() throws WalletException{

        given(this.walletJpaRepository.getWalletById(2))
                .willReturn((new WalletDto(2, "Navya", 24000.0,"abc@gmail.com",456)));
        given(this.walletJpaRepository.getWalletById(4))
                .willReturn((new WalletDto(4, "Sri", 30000.0,"bgy@gmail.com",854)));

        Double newBalance=this.walletService.withdrawFundsFromWalletById(2,400.0);
        assertEquals(Optional.of(23600.0),newBalance);
    }
    @Test
    public void withdrawFundsFromWalletInsufficientFundExceptionTest() throws WalletException{

        given(this.walletJpaRepository.getWalletById(2))
                .willReturn((new WalletDto(2, "Navya", 24000.0,"abc@gmail.com",456)));
        given(this.walletJpaRepository.getWalletById(4))
                .willReturn((new WalletDto(4, "Sri", 30000.0,"bgy@gmail.com",854)));


        assertThrows(WalletException.class,()->this.walletService.withdrawFundsFromWalletById(2,25000.0));
    }
    @Test
    public void withdrawFundsFromWalletInsufficientFundExceptionMessageTest() throws WalletException{

        given(this.walletJpaRepository.getWalletById(2))
                .willReturn((new WalletDto(2, "Navya", 24000.0,"abc@gmail.com",456)));
        given(this.walletJpaRepository.getWalletById(4))
                .willReturn((new WalletDto(4, "Sri", 30000.0,"bgy@gmail.com",854)));
        String eMessage="";
        try{
            this.walletService.withdrawFundsFromWalletById(2,26000.0);
        }
        catch (WalletException e){
            eMessage=e.getMessage();
        }
        assertEquals("Insufficient balance, current balance:24000.0",eMessage);
    }
}


