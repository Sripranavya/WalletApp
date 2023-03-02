package com.demo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collection;

@Service
public class WalletServiceImpl implements  WalletService{

   // autowiring walletRepository
    @Autowired
    private WalletRepository walletRepository;
    @Override
    public WalletDto registerWallet(WalletDto wallet) throws WalletException
    {
        return walletRepository.createWallet(wallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        WalletDto foundWallet = walletRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletException("Wallet Id is invalid or does not exist.");

        return foundWallet;
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException {
        return walletRepository.updateWallet(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        WalletDto foundWallet = walletRepository.getWalletById(walletId);
        if(foundWallet==null)
            throw new WalletException("Wallet coudn't be deleted or Id not found:"+ walletId);
        return walletRepository.deleteWalletById(walletId);
    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        WalletDto foundWallet = walletRepository.getWalletById(walletId);
        if(foundWallet==null)
            throw new WalletException("Wallet ID is invalid or doesn't exists.");
        Double newBalance = foundWallet.getBalance()+amount;
        foundWallet.setBalance(newBalance);
        walletRepository.updateWallet(foundWallet);
        return newBalance;
    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletException
    {
        WalletDto foundWallet = walletRepository.getWalletById(walletById);
        if(foundWallet==null)
            throw new WalletException("Wallet has no amount or Wallet ID is invalid!");
        else if(amount<0)
            throw new WalletException("Given Amount is invalis");
        else if(foundWallet.getBalance()>=amount)
            throw new WalletException("Amount is greater than the wallet amount, enter lesser amount");

        Double balance = foundWallet.getBalance();
        foundWallet.setBalance(balance);
        this.walletRepository.updateWallet(foundWallet);
        return balance;

    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {
        WalletDto foundWallet = walletRepository.getWalletById(fromWalletId);
        if(foundWallet==null)
            throw new WalletException("Wallet has no amount or Wallet ID is invalid!");
        WalletDto fromWallet = this.walletRepository.getWalletById(fromWalletId);
        if(fromWallet == null)
            throw new WalletException("From wallet does not exists, id:"+fromWalletId);
        WalletDto toWallet = this.walletRepository.getWalletById(toWalletId);
        if(toWallet== null)
            throw new WalletException("To wallet does not exists, id:"+toWalletId);
        Double fromBalance = fromWallet.getBalance();
        if(fromBalance<amount)
            throw new WalletException("Insufficient balance, current balance:"+fromBalance);

        fromWallet.setBalance(fromBalance-amount);

        Double toBalance = toWallet.getBalance();
        toWallet.setBalance(toBalance+amount);

        this.walletRepository.updateWallet(fromWallet);
        this.walletRepository.updateWallet(toWallet);
        return true;

    }

    @Override
    public Collection<WalletDto> getAllWallets() {
        return this.walletRepository.getAllWallets();
    }
}
