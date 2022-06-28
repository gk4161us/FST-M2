package liveProject;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("UserProvider")
@PactFolder("target/pacts")
public class PactProviderTest {

        @BeforeEach
        void before(PactVerificationContext context){
            //set target for provider to send request to
            context.setTarget(new HttpTestTarget("localhost", 8585));
        }
        @TestTemplate
        @ExtendWith(PactVerificationInvocationContextProvider.class)
        void pactTestTemplate(PactVerificationContext context){
            //Verify the interaction between consumer and provider
            //using the contract generated in target/pacts
            context.verifyInteraction();
        }
        //state to send the call to consumer
        @State("A request to create a user")
        public void sampleState(){

        }


    }

