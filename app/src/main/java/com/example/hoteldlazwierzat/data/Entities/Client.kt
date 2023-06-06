package com.example.hoteldlazwierzat.data.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "clients_table")
class Client(@PrimaryKey(autoGenerate = true)val id:Int = 0,
             var username:String,
             var name:String="DEFAULT",
             var surname:String="DEFAULT",
             var email:String="DEFAULT",
             var password:String,
             var phoneNumber:String = "DEFAULT",
             var address:String = "DEFAULT",
             var city:String = "DEFAULT",
             var postalCode:String = "DEFAULT",
             var province:String = "DEFAULT",
             ) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString() = "id: $id;name: $name; username: $username; email: $email;surname: $surname;"
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(phoneNumber)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeString(postalCode)
        parcel.writeString(province)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Client> {
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }
}