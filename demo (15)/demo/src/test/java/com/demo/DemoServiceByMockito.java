package com.demo;
import com.demo.Wallet.WalletService;
import com.demo.Wallet.WalletException;
import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class DemoServiceByMockito {
    @Autowired
    private WalletService walletService;
    @MockBean
    private WalletRepository walletRepository;
    @Test
    public void MockRepositoryCreateTest1()throws WalletException{
        WalletDto wallOne = new WalletDto(1,"Pranavya",30000.0,"xyz@gmail.com",256);
        given(this.walletRepository.createWallet(wallOne)).willReturn(wallOne);
        assertEquals("Pranavya",walletService.registerWallet(wallOne).getName());
    }
    @Test
    public void MockRepositoryGetTest1() throws WalletException
    {
        given(this.walletRepository.getWalletById(100))
                .willReturn(new WalletDto(100,"Pranavya", 30000.0,"xyz@gmail.com",256));
        assertEquals("Pranavya",walletService.getWalletById(100).getName());
    }

    @Test
    public void MockRepositoryUpdateTest1() throws WalletException
    {
        WalletDto walletDto= new WalletDto(2,"Navya",24000.0,"abc@gmail.com",756);
        given(this.walletRepository.updateWallet(walletDto))
                .willReturn(new WalletDto(2,"Navya",24000.0,"abc@gmail.com",456));
        assertEquals(456,walletService.getWalletById(2).getPwd());
    }
    @Test
    public void MockRepositoryDeleteTest1() throws WalletException{

        given(this.walletRepository.deleteWalletById(1))
                .willReturn(null);
        assertThrows(WalletException.class,()->walletService.getWalletById(1));
    }
}
