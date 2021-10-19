package io.khatabook.repository;

import io.khatabook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.user_id = ?1")
    User findByUser_id(long id);

    @Query("select new java.lang.String(u.business_name) from User u where u.user_id = :id")
    String findBusinessName(long id);

    @Modifying
    @Query("update User u set  u.balance =?2 where u.user_id=?1")
    void updateBalance(long id, long newBalance);

    @Query("select new java.lang.Long(u.balance) from User u where u.user_id = :id")
    Long findBalance(long id);

    @Query("select new java.lang.Long(u.user_id) from User u where u.phone = :phone")
    Long findUserId(long phone);

    @Query("select (count(u) > 0) from User u where u.phone = ?1")
    boolean existsUserByPhone(long phone);
}