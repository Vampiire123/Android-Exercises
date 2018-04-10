package com.example.syl.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class GetSharedLinkTest {

    @Mock
    GetSharedLink mockGetSharedLink;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAMockedValueForAGivenSynchronousCall() {
        givenThereIsAShareLink();

        assertEquals(mockGetSharedLink.getLink(), "http://www.link.com/");
    }

    private void givenThereIsAShareLink() {
        when(mockGetSharedLink.getLink()).thenReturn("http://www.link.com/");
    }
}