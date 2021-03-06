/*
 * Copyright 2017 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.Grpc;
import io.grpc.NameResolver;
import io.grpc.SecurityLevel;
import java.util.List;

/**
 * Special attributes that are only useful to gRPC.
 */
public final class GrpcAttributes {
  /**
   * Attribute key for gRPC LB server addresses.
   *
   * <p>Deprecated: this will be used for grpclb specific logic, which will be moved out of core.
   */
  @Deprecated
  @NameResolver.ResolutionResultAttr
  public static final Attributes.Key<List<EquivalentAddressGroup>> ATTR_LB_ADDRS =
      Attributes.Key.create("io.grpc.grpclb.lbAddrs");

  /**
   * The naming authority of a gRPC LB server address.  It is an address-group-level attribute,
   * present when the address group is a LoadBalancer.
   *
   * <p>Deprecated: this will be used for grpclb specific logic, which will be moved out of core.
   */
  @Deprecated
  @EquivalentAddressGroup.Attr
  public static final Attributes.Key<String> ATTR_LB_ADDR_AUTHORITY =
      Attributes.Key.create("io.grpc.grpclb.lbAddrAuthority");

  /**
   * Whether this EquivalentAddressGroup was provided by a GRPCLB server. It would be rare for this
   * value to be {@code false}; generally it would be better to not have the key present at all.
   */
  @EquivalentAddressGroup.Attr
  public static final Attributes.Key<Boolean> ATTR_LB_PROVIDED_BACKEND =
      Attributes.Key.create("io.grpc.grpclb.lbProvidedBackend");

  /**
   * The security level of the transport.  If it's not present, {@link SecurityLevel#NONE} should be
   * assumed.
   */
  @Grpc.TransportAttr
  public static final Attributes.Key<SecurityLevel> ATTR_SECURITY_LEVEL =
      Attributes.Key.create("io.grpc.internal.GrpcAttributes.securityLevel");

  /**
   * Attribute key for the attributes of the {@link EquivalentAddressGroup} ({@link
   * EquivalentAddressGroup#getAttributes}) that the transport's server address is from.  This is a
   * client-side-only transport attribute, and available right after the transport is started.
   */
  @Grpc.TransportAttr
  public static final Attributes.Key<Attributes> ATTR_CLIENT_EAG_ATTRS =
      Attributes.Key.create("io.grpc.internal.GrpcAttributes.clientEagAttrs");

  private GrpcAttributes() {}
}
