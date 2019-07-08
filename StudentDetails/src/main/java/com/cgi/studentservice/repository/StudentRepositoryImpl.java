package com.cgi.studentservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Pushpanjali
 * 
 * This class is used for implementing any custom repository details.
 * more specifically for any user needs which are not addressed in crud/JPA repository
 * 
 */
public class StudentRepositoryImpl implements CustomStudentRepository {
    @Autowired
    private EntityManager em;
    
    /**
     * This is a implementation method used for pagination in UI.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findPagedResultForStudents(int offset, int limit) {
        Query query = em.createQuery("select s.id,s.name,s.birthDate,s.address,s.regNo from Student s order by s.id desc");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<Object[]> resultList =  query.getResultList();
        return resultList;
    }
}