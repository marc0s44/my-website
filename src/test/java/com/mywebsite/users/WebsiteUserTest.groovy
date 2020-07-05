package com.mywebsite.users

import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.ValidatorFactory

class WebsiteUserTest extends Specification {
    @Unroll
    def "Should create violations when user body has wrong structure"() {
        given:
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        def validator = factory.getValidator()
        when:
        WebsiteUser websiteUser = new WebsiteUser()
        websiteUser.setName(name)
        websiteUser.setSurname(surname)
        websiteUser.setPassword(password)
        websiteUser.setEmail(email)
        Set<ConstraintViolation<WebsiteUser>> violations = validator.validate(websiteUser)
        then:
        !violations.isEmpty() == true
        where:
        name   | surname | password   | email
        "John" | "Smith" | ""         | "John@Smith.com"
        null   | null    | null       | null
        ""     | null    | "password" | "john.smith@gmail.com"
        "Bill" | "Brown" | "    "     | "bill.brown@gmail.com"
    }
}
