package com.spr.service;

import com.spr.exception.ClientNotFound;
import com.spr.model.Client;
import com.spr.model.Account;
import java.util.List;


public interface ClientService {

    public Client create(Client client);
    public Client update(Client client) throws ClientNotFound;
    public Client findById(Integer id);
    public List<Client> findAllClients();
    public List<Account> findClientAccounts(Integer id);
}