package com.example.syl.mock;

import com.example.syl.mock.model.AppError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class GetSharedLinkTest {

    @Mock
    GetSharedLink mockGetSharedLink;

    @Mock
    GetSharedLink.Listener mockGetSharedLinkListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAMockedValueForAGivenSynchronousCall() {
        givenThereIsAShareLink();

        assertEquals(mockGetSharedLink.getLink(), "http://www.link.com/");
    }

    @Test
    public void shouldReturnAMockedValueForAGivenASynchronousCallOnSuccess() {
        givenAMockedInstanceThatReturnsSuccess();

        mockGetSharedLink.getLinkAsync(mockGetSharedLinkListener);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockGetSharedLinkListener).onSuccess(captor.capture());

        assertEquals("http://www.link.com/", captor.getValue());
    }

    @Test
    public void shouldReturnAMockedValueForAGivenASynchronousCallOnFailure() {
        givenAMockedInstanceThatReturnsFailure();

        mockGetSharedLink.getLinkAsync(mockGetSharedLinkListener);

        ArgumentCaptor<AppError> captor = ArgumentCaptor.forClass(AppError.class);

        verify(mockGetSharedLinkListener).onFailure(captor.capture());

        assertEquals("Could not create link", captor.getValue().message());
    }

    private void givenThereIsAShareLink() {
        when(mockGetSharedLink.getLink()).thenReturn("http://www.link.com/");
    }

    private void givenAMockedInstanceThatReturnsSuccess() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetSharedLink.Listener) invocation.getArguments()[0]).onSuccess("http://www.link.com/");
                return null;
            }
        }).when(mockGetSharedLink).getLinkAsync(any(GetSharedLink.Listener.class));
    }

    private void givenAMockedInstanceThatReturnsFailure() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((GetSharedLink.Listener) invocation.getArguments()[0])
                        .onFailure(new AppError("Could not create link"));
                return null;
            }
        }).when(mockGetSharedLink).getLinkAsync(any(GetSharedLink.Listener.class));
    }
}