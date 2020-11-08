package islamic.soft.saeedi.com.archjava2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student
{

    @PrimaryKey(autoGenerate = true)
    int ID;
    String name;
    String mob;
    String address;

    public Student( String name, String mob, String address)
    {
        this.name = name;
        this.mob = mob;
        this.address = address;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMob()
    {
        return mob;
    }

    public void setMob(String mob)
    {
        this.mob = mob;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
