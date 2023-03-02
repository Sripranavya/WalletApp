package com.demo.Wallet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;


@RequestMapping(value="v1")
@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;


    @GetMapping("/greet")
    public String greet()
    {
        return "Welcome to my demo!";
    }

    @GetMapping("/wallet/{id}")
    public WalletDto getWalletById(@PathVariable Integer id) throws WalletException
    {
        return walletService.getWalletById(id);
    }
    @PostMapping("/wallet")
    @ResponseStatus(value = HttpStatus.CREATED)
    public WalletDto addWallet(@RequestBody WalletDto wallet) throws WalletException
    {
        return walletService.registerWallet(wallet);
    }

    @PutMapping("/wallet")
    public WalletDto replaceWallet(@RequestBody WalletDto walletDto) throws WalletException
    {
        return walletService.updateWallet(walletDto);
    }

    @DeleteMapping("/wallet/{walletId}")
    public WalletDto deleteWallet(@PathVariable("walletId") Integer walletDto ) throws WalletException
    {
        return walletService.deleteWalletById(walletDto);
    }

    @PatchMapping("/wallet/{id}/name/{walletName}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateWallet(@PathVariable("id") Integer walletId,@PathVariable("walletName") String walletName)
    {
        return "Patch !"+walletId+":"+walletName;
    }
    @PatchMapping("wallet/{id}/{amount}")
    public Double addFundsToWalletById(@PathVariable("id")Integer walletId,@PathVariable("amount") Double amount) throws WalletException {
        return this.walletService.addFundsToWalletById(walletId,amount);
    }
    @PatchMapping("wallet/{id}/fund/{amount}")
    public Double withdrawFundsfromWalletById(@PathVariable("id")Integer walletId,@PathVariable("amount") Double amount) throws WalletException {
        return this.walletService.withdrawFundsFromWalletById(walletId,amount);
    }

    @PostMapping("wallet/fund/{fromId}/{toId}/{amount}")
    public Boolean fundTransfer(@PathVariable("fromId")Integer fromWalletId,@PathVariable("toId")Integer toWalletId , @PathVariable("amount") Double amount) throws WalletException {
        return this.walletService.fundTransfer(fromWalletId,toWalletId,amount);
    }


    @GetMapping("wallets")
    public Collection<WalletDto> getAllWallets(){
        return this.walletService.getAllWallets();
    }

}
