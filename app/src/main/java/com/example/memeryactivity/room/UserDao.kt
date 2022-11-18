package com.example.memeryactivity.room

import androidx.room.*


@Dao
interface UserDao {
    //查询
    @Query("SELECT * FROM user")
    fun getUsers(): List<User?>?

    //根据id查询
    @Query("SELECT * FROM user WHERE name = :name")
    fun getUserByName(name: String): User?

    //插入一条数据
    @Insert
    fun insertUser(user: User)

    //删除一条数据
    @Delete
    fun deleteUser(user: User)

    //更新一条数据
    @Update
    fun updateUser(user: User?)
}