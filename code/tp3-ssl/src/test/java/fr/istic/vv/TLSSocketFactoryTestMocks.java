import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols() {
        // Socket Declaration / initialisation
        TLSSocketFactory factory = new TLSSocketFactory();
        // Mock production
        SSLSocket mockChaussette = mock(SSLSocket.class);
        // Mock setUp
        when(mockChaussette.getSupportedProtocols()).thenReturn(null);
        when(mockChaussette.getEnabledProtocols()).thenReturn(null);
        doNothing().when(mockChaussette).setEnabledProtocols(new String[]{});
        // Mock use for the testCase
        factory.prepareSocket(mockChaussette);
        // Oracle Mosckito's style BzzZZZzzzZZZzzzZZZZzzzZZZZZ
        verify(mockChaussette, times(1)).getEnabledProtocols();
        verify(mockChaussette, times(0)).setEnabledProtocols(new String[]{});
        verify(mockChaussette, times(1)).getSupportedProtocols();
    }

    @Test
    public void typical() {
        TLSSocketFactory factory = new TLSSocketFactory();
        SSLSocket mockChaussette = mock(SSLSocket.class);
        when(mockChaussette.getSupportedProtocols()).thenReturn(shuffle(
                new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(mockChaussette.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));
        doNothing().when(mockChaussette).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
        factory.prepareSocket(mockChaussette);
        verify(mockChaussette, times(1)).getEnabledProtocols();
        verify(mockChaussette, times(1)).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});

    }


    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }
}