package com.spr.service;


import java.util.List;
import javax.annotation.Resource;
import com.spr.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spr.exception.ClientNotFound;
import com.spr.repository.ClientRepository;
import com.spr.model.Client;
import com.spr.model.Account;

@Service
public class ClientServiceImplementation implements ClientService {

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Client create(Client client) {
        Client createdClient = client;
        return clientRepository.save(createdClient);
    }

    @Override
    @Transactional
    public Client findById(Integer id) {

        return clientRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor=ClientNotFound.class)
    public Client update(Client client) throws ClientNotFound {
        Client updatedClient= clientRepository.findOne(client.getId());

        if (updatedClient == null)
            throw new ClientNotFound();

        updatedClient.setName(client.getName());
        updatedClient.setCardNumber(client.getCardNumber());
        updatedClient.setAddress(client.getAddress());
        return updatedClient;
    }

    @Override
    @Transactional
    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client findClientByNumericCode(String numericCode){
        return clientRepository.findByNumericCode(numericCode);
    }

    @Override
    @Transactional
    public List<Account> findAccountsByClientId(Integer id){
        return accountRepository.findByClientID(id);
    }
}
