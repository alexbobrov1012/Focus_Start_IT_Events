package com.example.iteventscheckin.models

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable


@Entity(tableName = "members_table")
class Member : Serializable {

    @PrimaryKey
    var id: Int = 0

    var eventId: Int = 0

    var phone: String? = null

    var city: String? = null

    var company: String? = null

    var position: String? = null

    var addition: String? = null

    var registeredDate: String? = null

    var isVisited: Boolean = false

    var agreedByManager: String? = null

    var lastName: String? = null

    var firstName: String? = null

    var patronymic: String? = null

    var email: String? = null

    override fun toString(): String {
        return "Member{" +
                "id=" + id +
                ", phone='" + phone + '\''.toString() +
                ", city='" + city + '\''.toString() +
                ", company='" + company + '\''.toString() +
                ", position='" + position + '\''.toString() +
                ", addition='" + addition + '\''.toString() +
                ", registeredDate='" + registeredDate + '\''.toString() +
                ", isVisited=" + isVisited +
                ", agreedByManager='" + agreedByManager + '\''.toString() +
                ", lastName='" + lastName + '\''.toString() +
                ", firstName='" + firstName + '\''.toString() +
                ", patronymic='" + patronymic + '\''.toString() +
                ", email='" + email + '\''.toString() +
                '}'.toString()
    }
}
