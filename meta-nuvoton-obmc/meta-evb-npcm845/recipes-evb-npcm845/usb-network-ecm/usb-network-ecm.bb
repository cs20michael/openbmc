FILESEXTRAPATHS:append := "${THISDIR}/files:"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS += "systemd"
RDEPENDS:${PN} += "libsystemd"

SRC_URI += "file://usb_network_ecm.sh \
           file://usb_network_ecm.service \
           file://00-bmc-ecm0-override.network \
           file://10-bmc-usb-ecm.link \
           file://10-bmc-usb-ecm-host.link \
           file://00-bmc-usb-ecm-host.network \
           "

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}/${sbindir}
    install -m 0755 ${UNPACKDIR}/usb_network_ecm.sh ${D}/${sbindir}

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${UNPACKDIR}/usb_network_ecm.service ${D}${systemd_unitdir}/system

    install -d ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${UNPACKDIR}/00-bmc-ecm0-override.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${UNPACKDIR}/00-bmc-usb-ecm-host.network ${D}${sysconfdir}/systemd/network

    install -d ${D}${base_libdir}/systemd/network/
    install -m 0644 ${UNPACKDIR}/10-bmc-usb-ecm.link ${D}${base_libdir}/systemd/network
    install -m 0644 ${UNPACKDIR}/10-bmc-usb-ecm-host.link ${D}${base_libdir}/systemd/network
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "usb_network_ecm.service"
FILES:${PN} += "${sysconfdir}/systemd/network/00-bmc-ecm0-override.network"
FILES:${PN} += "${base_libdir}/systemd/network/10-bmc-usb-ecm.link"

FILES:${PN} += "${sysconfdir}/systemd/network/00-bmc-usb-ecm-host.network"
FILES:${PN} += "${base_libdir}/systemd/network/10-bmc-usb-ecm-host.link"

inherit allarch systemd
