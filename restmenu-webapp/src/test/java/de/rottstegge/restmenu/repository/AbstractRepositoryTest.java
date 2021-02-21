package de.rottstegge.restmenu.repository;

import de.rottstegge.restmenu.model.base.BaseEntity;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.Assert.*;

public abstract class AbstractRepositoryTest<T extends BaseEntity> {

    protected abstract JpaRepository<T, Long> getRepository();

    protected abstract T getCreateEntity();

    protected abstract void modifyUpdateEntity(T entity);

    protected abstract Example<T> getExample();

    @Test
    @Transactional
    public void testDeleteById() {
        T entity = getRepository().save(getCreateEntity());
        getRepository().deleteById(entity.getId());
        assertFalse(getRepository().findById(entity.getId()).isPresent());
    }

    @Test
    @Transactional
    public void testDeleteAll() {
        getRepository().save(getCreateEntity());
        getRepository().deleteAll();
        assertEquals(0, getRepository().count());
    }

    @Test
    @Transactional
    public void testFindById() {
        T entity = getRepository().save(getCreateEntity());
        assertNotNull(getRepository().findById(entity.getId()));
    }

    @Test
    @Transactional
    public void testGetOne() {
        T entity = getRepository().save(getCreateEntity());
        assertNotNull(getRepository().getOne(entity.getId()));
    }

    @Test
    @Transactional
    public void testExistsById() {
        T entity = getRepository().save(getCreateEntity());
        assertTrue(getRepository().existsById(entity.getId()));
    }

    @Test
    @Transactional
    public void testFindAll() {
        long size = getRepository().count();

        getRepository().save(getCreateEntity());
        assertEquals(size + 1, getRepository().count());
    }

    @Test
    @Transactional
    public void testfindAllById() {
        T entity = getRepository().save(getCreateEntity());
        assertEquals(1,
                getRepository()
                        .findAllById(Collections.singletonList(entity.getId()))
                        .size());
    }

    @Test
    @Transactional
    public void testFindAllSorted() {
        long size = getRepository().count();

        getRepository().save(getCreateEntity());
        assertEquals(size + 1,
                getRepository().findAll(Sort.by(Sort.Direction.DESC, "id")).size());
    }

    @Test
    @Transactional
    public void testFindAllPaged() {
        getRepository().save(getCreateEntity());
        assertEquals(1, getRepository().findAll(PageRequest.of(0, 1))
                .getNumberOfElements());
    }

    @Test
    @Transactional
    public void testFindAllByPredicate() {
        getRepository().save(getCreateEntity());
        assertEquals(1, getRepository().findAll(getExample()).size());
    }

    @Test
    @Transactional
    public void testFindAllByPredicateOrdered() {
        getRepository().save(getCreateEntity());
        assertEquals(1,
                getRepository().findAll(getExample(), Sort.by(Sort.Direction.DESC, "id"))
                        .size());
    }

    @Test
    @Transactional
    public void testFindAllByPredicatePaged() {
        getRepository().save(getCreateEntity());
        assertEquals(1,
                getRepository().findAll(getExample(), PageRequest.of(0, 1))
                        .getNumberOfElements());
    }

    @Test
    @Transactional
    public void testCount() {
        long size = getRepository().count();

        getRepository().save(getCreateEntity());
        assertEquals(size + 1, getRepository().count());
    }

    @Test
    @Transactional
    public void testCountByPredicate() {
        getRepository().saveAndFlush(getCreateEntity());
        assertEquals(1, getRepository().count(getExample()));
    }

    @Test
    @Transactional
    public void testCreate() {
        long size = getRepository().count();

        getRepository().save(getCreateEntity());
        assertEquals(size + 1, getRepository().count());
    }

    @Test
    @Transactional
    public void testUpdate() {
        T entity = getRepository().save(getCreateEntity());
        modifyUpdateEntity(entity);
        getRepository().save(entity);
    }

}
