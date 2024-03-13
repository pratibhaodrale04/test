1. Create UserInfo Entity class to store user details.
2. Create IUserInfo in repo package to create jpa-mapping using hibernate.
3. Create UserInfoConfig class which implements UserDetails interface, 
    which provides core user information which is later encapsulated into Authentication Object.