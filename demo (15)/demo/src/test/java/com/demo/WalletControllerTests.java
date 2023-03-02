package com.demo;

import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WalletControllerTests {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testrestTemplate;

    @BeforeEach
    public void start()
    {
        this.testrestTemplate.postForObject("http://localhost:" + port + "/v1/wallet",new WalletDto(1,"Pranavya",30000.0,"xyz@gmail.com",256),WalletDto.class);
    }
    @Test
    public void greetingMessage() throws WalletException
    {
        assertThat(this.testrestTemplate.getForObject("http://localhost:" + port + "/v1/",
                String.class)).contains("Welcome to my demo!");
    }

    @Test
    public void getWalletByIdTest() throws WalletException {
        WalletDto foundWallet =this.testrestTemplate.getForObject("http://localhost:" + port + "/v1/wallet/1", WalletDto.class);
        assertEquals("Pranavya",foundWallet.getName());
    }

    @Test
    public void getWalletByIdExceptionTest() throws WalletException {
        String walletExceptionMessage =this.testrestTemplate.getForObject("http://localhost:" + port + "/v1/wallet/2", String.class);
        assertEquals("Wallet Id does not exist or Wallet Id is invalid.",walletExceptionMessage);
    }
}
