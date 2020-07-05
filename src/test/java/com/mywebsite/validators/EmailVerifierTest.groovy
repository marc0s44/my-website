package com.mywebsite.validators

import spock.lang.Specification
import spock.lang.Unroll

class EmailVerifierTest extends Specification {

    @Unroll
    def "Should return true for properly structured emails: #email"() {
        when:
        def isValid = EmailVerifier.isEmailValid(email)
        then:
        isValid == true
        where:
        email << [
                "John@gmail.com",
                "fake.email@yahoo.com",
                "email@example.com",
                "firstname.lastname@example.com",
                "email@subdomain.example.com",
                "firstname+lastname@example.com",
                "1234567890@example.com",
                "email@example-one.com",
                "email@example.name",
                "email@example.museum",
                "firstname-lastname@example.com"
        ]
    }

    @Unroll
    def "Should return false for wrongly structured emails: #email"() {
        when:
        def isValid = EmailVerifier.isEmailValid(email)
        then:
        isValid == false
        where:
        email << [
                "plainaddress",
                "@example.com",
                "Joe Smith <email@example.com>",
                "email.example.com",
                "email@example@example.com",
                ".email@example.com",
                "email.@example.com",
                "email..email@example.com",
                "あいうえお@example.com",
                "email@example.com (Joe Smith)",
                "email@example",
                "email@111.222.333.44444",
                "email@example..com",
                "Abc..123@example.com"
        ]
    }
}
