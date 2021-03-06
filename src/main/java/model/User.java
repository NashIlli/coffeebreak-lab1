package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="USER")

public class User implements Comparable{
	
	@Column(name= "USER_NICKNAME", nullable = false, unique=true)
	private String nickName;
	@Column(name= "USER_PASS", nullable = false)
	private String password;
    @Column(nullable = false,unique=true)
    private String email;
    @Column(nullable = false)
    private boolean isAdmin;
	@Embedded @OneToOne
	private Location lastLocation;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<User> friends;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Place> favouritePlaces;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<Invitation> invitations;
    @Column(nullable = false)
    private boolean deleted;



	public User(String name, String pass, String mail){
		this.nickName=name;
		this.password=pass;
        this.email=mail;
        setAdmin(false);
        setDeleted(false);
        friends = new HashSet<User>();
        favouritePlaces = new HashSet<Place>();
        invitations = new HashSet<Invitation>();

	}

     public User() {
    }

    public void addFriend(User friend){
       friends.add(friend);
    }

	public String getNickName(){
		return nickName;
	}

	public void setNickName(String userName) {
		this.nickName = userName;
	}


	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getId(){
		return userId;
	}
	
	public void setId(long id){
		this.userId=id;
	}
	
	
	public Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

    public Set<User> getFriends() {

        Set<User> aux = new HashSet<User>();
        Iterator<User> iterator = friends.iterator();
        if(friends.isEmpty()){
            return friends;
        }  else{
            while(iterator.hasNext()){
                User friend = iterator.next();
                if(!friend.isDeleted()){
                   aux.add(friend);
                }
            }
            friends=aux;

            return friends;
        }

    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public void setFavouritePlaces(Set<Place> favouritePlaces) {
        this.favouritePlaces = favouritePlaces;
    }

    public Set<Place> getFavouritePlaces() {

        return favouritePlaces;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int compareTo(Object o) {
        return this.getNickName().compareTo(((User)o).getNickName());
    }

    public Set<Invitation> getInvitations() {
        return invitations;
    }

    public void addInvitation (Invitation invitation) {
        invitations.add(invitation);
    }

    public void setDeleted(boolean deleted){
        this.deleted=deleted;
    }

    public boolean isDeleted(){
        return deleted;
    }


}

