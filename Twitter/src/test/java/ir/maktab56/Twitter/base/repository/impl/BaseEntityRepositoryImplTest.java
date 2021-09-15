package ir.maktab56.Twitter.base.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import ir.maktab56.Twitter.domain.Profile;
import org.junit.jupiter.api.*;
import java.util.*;


class BaseEntityRepositoryImplTest {

    private BaseEntityRepositoryImpl<Profile, Long> accountRepository;
    private static Profile account;
    private static final Profile account1 = new Profile();
    private static List<Profile> accounts;


    @BeforeAll
    public static void beforeAll(){
        account = new Profile();
        account.setFirstName("Morteza");
        account.setLastName("Ahangari");
        account.setUsername("mAhangari");
        account.setPassword("Dan9011216");

        account1.setFirstName("Ali");

        Profile account2 = new Profile();
        account2.setFirstName("Ahmad");

        accounts = new ArrayList<>(Arrays.asList(
           account1,
           account2
        ));
    }

    @BeforeEach
    public void init(){

        accountRepository = mock(BaseEntityRepositoryImpl.class);

        when(accountRepository.findById(anyLong())).thenReturn(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountRepository.findAllById(Arrays.asList(1L, 2L))).thenReturn(accounts);
        when(accountRepository.existsById(1L)).thenReturn(true);
        when(accountRepository.save(account)).thenReturn(account);
        doNothing().when(accountRepository).delete(account);
        //when(accountRepository.save()).thenReturn(account);
    }

    @Test
    @DisplayName("Test Save method")
    void save() {
        Profile newAccount = accountRepository.save(account);
        assertNotNull(newAccount);
        newAccount = accountRepository.save(account1);
        assertNull(newAccount);
    }

    @Test
    void findAllById() {
        List<Profile> profiles = accountRepository.findAllById(Arrays.asList(1L, 2L));
        assertEquals(2, profiles.size());
        profiles = accountRepository.findAllById(Arrays.asList(2L, 3L));
        assertEquals(0, profiles.size());
    }

    @Test
    void findAll() {
        List<Profile> profiles = accountRepository.findAll();
        assertEquals("Ali", profiles.get(0).getFirstName());
        assertEquals("Ahmad", profiles.get(1).getFirstName());
    }

    @Test
    void delete() {
        accountRepository.delete(account);
        accountRepository.delete(account);
        verify(accountRepository, times(2)).delete(account);
    }

    @Test
    void findById() {
        Profile actual = accountRepository.findById(1L);
        assertEquals("Morteza", actual.getFirstName());
    }

    @Test
    void existsById() {
        assertTrue(accountRepository.existsById(1L));
        assertFalse(accountRepository.existsById(2L));
    }
}