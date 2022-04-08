package emented.lab6.common.util;

import emented.lab6.common.abstractions.AbstractResponseMessage;
import emented.lab6.common.entities.MusicBand;

import java.io.Serializable;
import java.util.Set;

public class Response implements Serializable {

    private AbstractResponseMessage messageToResponse;
    private MusicBand bandToResponse;
    private Set<MusicBand> collectionToResponse;

    public Response(AbstractResponseMessage messageToResponse) {
        this.messageToResponse = messageToResponse;
    }

    public Response(AbstractResponseMessage messageToResponse, MusicBand bandToResponse) {
        this.messageToResponse = messageToResponse;
        this.bandToResponse = bandToResponse;
    }

    public Response(AbstractResponseMessage messageToResponse, Set<MusicBand> collectionToResponse) {
        this.messageToResponse = messageToResponse;
        this.collectionToResponse = collectionToResponse;
    }

    public Response(MusicBand bandToResponse) {
        this.bandToResponse = bandToResponse;
    }

    public Response(Set<MusicBand> collectionToResponse) {
        this.collectionToResponse = collectionToResponse;
    }

    public AbstractResponseMessage getMessageToResponse() {
        return messageToResponse;
    }

    public MusicBand getBandToResponse() {
        return bandToResponse;
    }

    public Set<MusicBand> getCollectionToResponse() {
        return collectionToResponse;
    }

    @Override
    public String toString() {
        StringBuilder collection = new StringBuilder();
        if (collectionToResponse != null) {
            for (MusicBand m : collectionToResponse) {
                collection.append(m.toString()).append("\n");
            }
            collection = new StringBuilder(collection.substring(0, collection.length() - 2));
        }
        return (messageToResponse == null ? "" : messageToResponse.getMessage())
                + (bandToResponse == null ? "" : "\n" + bandToResponse)
                + ((collectionToResponse == null) ? "" : "\n"
                + collection);
    }
}
