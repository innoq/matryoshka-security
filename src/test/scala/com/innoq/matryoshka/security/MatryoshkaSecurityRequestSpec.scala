package com.innoq.matryoshka.security

import org.scalatest.WordSpec
import org.springframework.mock.web.MockHttpServletRequest

class MatryoshkaSecurityRequestSpec extends WordSpec {

  "isSecure" must {

    "return true if the original request is already secure" in {
      val request = new MockHttpServletRequest
      request.setSecure(true)
      assert(new MatryoshkaSecurityRequest(request).isSecure)
    }

    "return true if the request has an 'X-Forwarded-Proto' header with value 'https'" in {
      val request = new MockHttpServletRequest
      request.setSecure(false)
      request.addHeader("X-Forwarded-Proto", "https")
      assert(new MatryoshkaSecurityRequest(request).isSecure)
    }

    "return false if the request has an 'X-Forwarded-Proto' header with any other value" in {
      val request = new MockHttpServletRequest
      request.setSecure(false)
      request.addHeader("X-Forwarded-Proto", "http")
      assert(!new MatryoshkaSecurityRequest(request).isSecure)
    }

    "return false if the request has no 'X-Forwarded-Proto' header" in {
      val request = new MockHttpServletRequest
      request.setSecure(false)
      assert(!new MatryoshkaSecurityRequest(request).isSecure)
    }

  }

}
