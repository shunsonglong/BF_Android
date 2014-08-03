package com.dt.zero.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {

    private String id;
    private String name;
    private String province;
    private String city;
    private String location;
    private String description;
    private String url;
    private String cover_image;
    private String created_at;
    private String Phone_number;
    
	public UserBean(Parcel source) {  
        id = source.readString();  
        name = source.readString();
        province = source.readString();
        city = source.readString();
        location = source.readString();
        description = source.readString();
        cover_image = source.readString();
        created_at = source.readString();
        Phone_number = source.readString();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getCoverImage() {
        return cover_image;
    }

    public void setCoverImage(String cover_image) {
        this.cover_image = cover_image;
    }
    
    public String getCreateAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }
    
    public String getPhoneNumber() {
        return Phone_number;
    }

    public void setPhoneNumber(String Phone_number) {
        this.Phone_number = Phone_number;
    }
    
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(province);
		dest.writeString(city);
		dest.writeString(location);
		dest.writeString(description);
		dest.writeString(url);
		dest.writeString(cover_image);
		dest.writeString(created_at);
		dest.writeString(Phone_number);
	}
	
	public static final Parcelable.Creator<UserBean> CREATOR =
            new Parcelable.Creator<UserBean>() {
                public UserBean createFromParcel(Parcel source) {
                	return new UserBean(source);
                }

                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };

}
