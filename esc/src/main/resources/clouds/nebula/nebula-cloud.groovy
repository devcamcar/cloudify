/***************
 * Cloud configuration file for the Nebula One cloud. Uses the default jclouds-based cloud driver.
 * See org.cloudifysource.dsl.cloud.Cloud for more details.
 * @author gabrielhurley
 *
 */

cloud {
    // Mandatory. The name of the cloud, as it will appear in the Cloudify UI.
    name = "Nebula One"

    /********
     * General configuration information about the cloud driver implementation.
     */
    configuration {
        // Optional. The cloud implementation class. Defaults to the build in jclouds-based provisioning driver.
        className "org.cloudifysource.esc.driver.provisioning.jclouds.DefaultProvisioningDriver"

        // Optional. The template name for the management machines. Defaults to the first template in the templates section below.
        managementMachineTemplate "SMALL_LINUX"

        // Optional. Indicates whether internal cluster communications should use the machine private IP. Defaults to true.
        connectToPrivateIp false

        bootstrapManagementOnPublicIp true

        // Optional. Path to folder where management state will be written. Null indicates state will not be written.
        persistentStoragePath persistencePath
    }

    /*************
     * Provider specific information.
     */
    provider {
        // Mandatory. The name of the provider.
        // When using the default cloud driver, maps to the Compute Service Context provider name.
        provider "openstack-nova"


        // Optional. The HTTP/S URL where cloudify can be downloaded from by newly started machines. Defaults to downloading the
        // cloudify version matching that of the client from the cloudify CDN.
        // Change this if your compute nodes do not have access to an internet connection, or if you prefer to use a
        // different HTTP server instead.
        // IMPORTANT: the default linux bootstrap script appends '.tar.gz' to the url whereas the default windows script appends '.zip'.
        // Therefore, if setting a custom URL, make sure to leave out the suffix.
        // cloudifyUrl "http://repository.cloudifysource.org/org/cloudifysource/2.6.1-5200-RELEASE/gigaspaces-cloudify-2.6.1-ga-b5200.zip"

        // Mandatory. The prefix for new machines started for servies.
        machineNamePrefix "cloudify-agent-"

        // Pptional. Files to be copied only to the management machines.
        managementOnlyFiles ([])

        // Optional. Logging level for the intenal cloud provider logger. Defaults to INFO.
        sshLoggingLevel "INFO"

        // Name of the new machine/s started as cloudify management machines. Names are case-insensitive.
        managementGroup "cloudify-manager-"

        // Number of management machines to start on bootstrap-cloud. In production, should be 2. Can be 1 for dev.
        numberOfManagementMachines 1

        // Approximate amount of RAM used by the OS + the base Cloudify Agent (GSA) on the machine.
        reservedMemoryCapacityPerMachineInMB 1024

    }

    /*************
     * Cloud authentication information
     */
    user {
        // Optional. Identity used to access cloud.
        // When used with the default driver, maps to the identity used to create the ComputeServiceContext.
        user "${tenant}:${user}"

        // Optional. Key used to access cloud.
        // When used with the default driver, maps to the credential used to create the ComputeServiceContext.
        apiKey apiKey
    }

    cloudCompute {
        /***********
         * Cloud machine templates available with this cloud.
         */
        templates ([
            SMALL_LINUX : computeTemplate{
                // Mandatory. Image ID.
                imageId ubuntuImageId
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 2048
                // Mandatory. Hardware ID.
                hardwareId smallFlavorId
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile keyFile

                username "ubuntu"
                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options ([
                    "securityGroupNames" : [securityGroup]as String[],
                    "keyPairName" : keyPair,
                    "generateKeyPair": false,
                    "autoAssignFloatingIp": true
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides ([
                    "jclouds.endpoint": openstackIdentityUrl
                ])

                // enable sudo.
                privileged true
            },
            SMALL_UBUNTU : computeTemplate{
                // Mandatory. Image ID.
                imageId ubuntuImageId
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 2048
                // Mandatory. Hardware ID.
                hardwareId smallFlavorId
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile keyFile

                username "ubuntu"
                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options ([
                    "securityGroupNames" : [securityGroup]as String[],
                    "keyPairName" : keyPair,
                    "generateKeyPair": false,
                    "autoAssignFloatingIp": true
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides ([
                    "jclouds.endpoint": openstackIdentityUrl
                ])

                // enable sudo.
                privileged true
            },
            MEDIUM_UBUNTU : computeTemplate{
                // Mandatory. Image ID.
                imageId ubuntuImageId
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 4096
                // Mandatory. Hardware ID.
                hardwareId mediumFlavorId
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile keyFile

                username "ubuntu"
                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options ([
                    "securityGroupNames" : [securityGroup]as String[],
                    "keyPairName" : keyPair,
                    "generateKeyPair": false,
                    "autoAssignFloatingIp": true
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides ([
                    "jclouds.endpoint": openstackIdentityUrl
                ])

                // enable sudo.
                privileged true
            },
            LARGE_UBUNTU : computeTemplate{
                // Mandatory. Image ID.
                imageId ubuntuImageId
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 8192
                // Mandatory. Hardware ID.
                hardwareId largeFlavorId
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile keyFile

                username "ubuntu"
                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options ([
                    "securityGroupNames" : [securityGroup]as String[],
                    "keyPairName" : keyPair,
                    "generateKeyPair": false,
                    "autoAssignFloatingIp": true
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides ([
                    "jclouds.endpoint": openstackIdentityUrl
                ])

                // enable sudo.
                privileged true
            }
        ])
    }

    /*****************
     * Optional. Custom properties used to extend existing drivers or create new ones.
     */
    custom ([:])
}
