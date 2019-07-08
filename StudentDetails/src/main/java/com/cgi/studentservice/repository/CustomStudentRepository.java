package com.cgi.studentservice.repository;

import java.util.List;

/**
 * 
 * @author Pushpanjali
 * 
 * This is a custom repository implemented for user needs(Pagination)
 *
 */
public interface CustomStudentRepository {
    List<Object[]> findPagedResultForStudents(int startIndex, int maxlimit);
}