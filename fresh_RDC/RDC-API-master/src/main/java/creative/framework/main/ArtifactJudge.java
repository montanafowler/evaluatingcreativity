/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.main;

/**
 *
* @author creapar team
 * @param <T>
 */
public interface ArtifactJudge <T> {
     public String evaluateArtifact(ArtifactContext<T> context, T artifact);
}
