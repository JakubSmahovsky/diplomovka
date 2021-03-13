package simpleT.dataStructures.query;

import java.util.ArrayList;

/**
 * A class holding info about queries requested by a protocol.
 */
public class Queries {
  public boolean executable = false;
  public ArrayList<Confidentiality> confidentiality = new ArrayList<>();
  public ArrayList<ForwardSecrecy> forwardSecrecy = new ArrayList<>();
}