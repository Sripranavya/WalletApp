package com.demo.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Collection;

public interface WalletJpaRepository extends JpaRepository<WalletDto,Integer>
{
    List<WalletDto> getByName(String name);
    WalletDto createWallet(WalletDto newWallet);
    WalletDto getWalletById(Integer  walletId);
    WalletDto updateWallet(WalletDto wallet);
   WalletDto deleteWalletById(Integer walletId);
    Collection<WalletDto> getAllWallets();
}

