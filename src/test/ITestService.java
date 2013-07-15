/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.jws.WebService;

/**
 *
 * @author sfeher
 */
@WebService
public interface ITestService {

    public void upload(final BigData data);
}
