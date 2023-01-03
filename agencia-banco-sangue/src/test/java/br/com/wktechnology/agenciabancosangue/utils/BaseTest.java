package br.com.wktechnology.agenciabancosangue.utils;

import br.com.wktechnology.agenciabancosangue.databuilders.domains.DomainsDataBuilder;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class BaseTest {

    protected DomainsDataBuilder domainsDataBuilder;

    protected Faker faker;

    @BeforeEach
    public void init() {
        this.domainsDataBuilder = new DomainsDataBuilder();
        this.faker = new Faker();
        MockitoAnnotations.openMocks(this);
    }
}
