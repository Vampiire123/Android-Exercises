package com.example.syl.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class GetSharedLinkTest {

    @Mock
    GetSharedLink mockGetSharedLink;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAMockedValueForAGivenSynchronousCall(){
        givenAllStringsMocked();

        assertEquals(mockGetSharedLink.getLink(), "link");
    }

    private void givenAllStringsMocked(){
        when(mockGetSharedLink.getLink()).thenReturn("link");
    }
}