
package com.juaracoding.absensi.model.imdb.post;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.juaracoding.absensi.application.AppController;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppController.class)
public class Post extends BaseModel implements Serializable, Parcelable
{

    @SerializedName("userId")
    @Expose
    @Column
    private Integer userId;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer idPost;
    @SerializedName("title")
    @Expose
    @Column
    private String title;
    @SerializedName("body")
    @Expose
    @Column
    private String body;
    public final static Creator<Post> CREATOR = new Creator<Post>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {
            return (new Post[size]);
        }

    }
    ;
    private final static long serialVersionUID = -28233401489564790L;

    protected Post(Parcel in) {
        this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idPost = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.body = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Post() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(idPost);
        dest.writeValue(title);
        dest.writeValue(body);
    }

    public int describeContents() {
        return  0;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }
}
