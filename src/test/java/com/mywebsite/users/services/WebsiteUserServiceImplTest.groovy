package com.mywebsite.users.services

import com.mywebsite.users.db.UsersDAO
import spock.lang.Specification

class WebsiteUserServiceImplTest extends Specification {

    private UsersDAO usersDAO = Mock()
    private WebsiteUserService websiteUserService = new WebsiteUserServiceImpl(usersDAO)

    def "GetUsers should return the same list that usersDAO gets"() {
        given:
        usersDAO.getUsers() >> [
                ["id1", "John", "Smith", "123", "John@Smith.com"],
                ["id2", "Bill", "Snow", "567", "Bill@Snow.com"]
        ]
        when:
        def users = websiteUserService.getUsers()
        then:
        users == [
                ["id1", "John", "Smith", "123", "John@Smith.com"],
                ["id2", "Bill", "Snow", "567", "Bill@Snow.com"]
        ]

    }
}
