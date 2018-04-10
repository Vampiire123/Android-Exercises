package com.example.syl.mock;

import com.example.syl.mock.model.AbsError;
import com.example.syl.mock.model.AppError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class GetSharedLinkTest {

    @Mock
    GetSharedLink mockGetSharedLink;

    @Mock
    GetSharedLink.Listener mockGetSharedLinkListener;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAMockedValueForAGivenSynchronousCall(){
        givenThereIsAShareLink();

        assertEquals(mockGetSharedLink.getLink(), "http://www.link.com/");
    }

    @Test
    public void shouldReturnAMockedValueForAGivenASynchronousCallOnSuccess(){
        givenAMockedGetSharedLinkListenerOnSuccess();

        mockGetSharedLink.getLinkAsync(mockGetSharedLinkListener);

        verify(mockGetSharedLinkListener).onSuccess("http://www.link.com/"); //captor.capture()
    }

    @Test
    public void shouldReturnAMockedValueForAGivenASynchronousCallOnFailure(){
        givenAMockedGetSharedLinkListenerOnFailure();

        mockGetSharedLink.getLinkAsync(mockGetSharedLinkListener);

        verify(mockGetSharedLinkListener).onFailure(new AppError("message"));
    }

    private void givenThereIsAShareLink(){
        when(mockGetSharedLink.getLink()).thenReturn("http://www.link.com/");
    }

    private void givenAMockedGetSharedLinkListenerOnSuccess(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetSharedLink.Listener) invocation.getArguments()[0]).onSuccess("http://www.link.com/");
                return null;
            }
        }).when(mockGetSharedLink).getLinkAsync(any(GetSharedLink.Listener.class));
    }

    private void givenAMockedGetSharedLinkListenerOnFailure(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetSharedLink.Listener) invocation.getArguments()[0]).onFailure(new AppError("message"));
                return null;
            }
        }).when(mockGetSharedLink).getLinkAsync(any(GetSharedLink.Listener.class));
    }
}