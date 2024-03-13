1. Create UserInfo Entity class to store user details.
2. Create IUserInfo in repo package to create jpa-mapping using hibernate.
3. Create UserInfoConfig class which implements UserDetails interface, 
   which provides core user information which is later encapsulated into Authentication Object.
4. Create UserInfoManagerConfig class which implements UserDetailsService interface, 
   used to retrieve user related data, using loadByUsername, and returns UserDetails object.
5. Create SecurityConfig file in config packageLets modify security settings, 
   to let it access the API using our User.
6. 