package ir.maktab56.Twitter.base.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ir.maktab56.Twitter.domain.Profile;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.*;


class BaseEntityRepositoryImplTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager em;
    private static TypedQuery query;

    private static BaseEntityRepositoryImpl accountRepository;
    private static Profile account;
    private static final Profile account1 = new Profile();
    private static final Profile account2 = new Profile();
    private static List<Profile> accounts;


    @BeforeAll
    public static void beforeAll() {
        account = new Profile();
        //account.setId(1L);
        account.setFirstName("Morteza");
        account.setLastName("Ahangari");
        account.setUsername("mAhangari");
        account.setPassword("Dan9011216");

        account1.setFirstName("Ali");
        account2.setFirstName("Ahmad");

        accounts = new ArrayList<>(Arrays.asList(
                account1,
                account2
        ));
        accountRepository = new BaseEntityRepositoryImpl(entityManagerFactory) {
            @Override
            public Class getEntityClass() {
                return Profile.class;
            }
        };
    }

    @BeforeEach
    public void init() {
        em = mock(EntityManager.class);
        query = mock(TypedQuery.class);
        accountRepository.em = em;

        when(em.createQuery("FROM Profile", Profile.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(accounts);

        when(em.find(Profile.class, 1L)).thenReturn(account);
        when(em.find(Profile.class, 2L)).thenReturn(account1);
        when(em.find(Profile.class, 3L)).thenReturn(account2);

        when(em.merge(account)).thenReturn(account);
        doNothing().when(em).persist(account);

        when(em.createQuery("UPDATE Profile SET isDeleted = 1 WHERE id =: id")).thenReturn(query);
        when(query.setParameter("id", account.getId())).thenReturn(query);

        when(em.createQuery(
                "SELECT COUNT(id)" +
                        " FROM Profile" +
                        " WHERE id =: id" +
                        " AND isDeleted = 0" +
                        " AND isActive = 1"
                , Long.class
        )).thenReturn(query);
        when(query.setParameter("id", 1L)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(1L);

    }

    @Test
    @DisplayName("Test Save method")
    void save() {
        Profile newAccount = (Profile) accountRepository.save(account);
        verify(em).persist(account);
        verify(em, never()).merge(account);
        assertNotNull(newAccount);
        assertEquals("Morteza", newAccount.getFirstName());
    }

    @Test
    void findAllById() throws Exception {
        List<Profile> profiles = accountRepository.findAllById(Arrays.asList(2L, 3L));
        assertEquals(2, profiles.size());
        assertEquals("Ali", profiles.get(0).getFirstName());
        assertEquals("Ahmad", profiles.get(1).getFirstName());
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
        verify(query).executeUpdate();
    }

    @Test
    void findById() {
        Profile actual = (Profile) accountRepository.findById(1L);
        assertEquals("Morteza", actual.getFirstName());
    }

    @Test
    void existsById() {
        boolean exists = accountRepository.existsById(1L);
        assertTrue(exists);
    }
}