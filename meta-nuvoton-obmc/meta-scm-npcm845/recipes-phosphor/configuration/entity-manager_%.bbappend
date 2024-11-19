FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
FILESEXTRAPATHS:prepend:m1120-c2195 :="${THISDIR}/${PN}/m1120:"

SRC_URI:append  = " file://scm.json"
SRC_URI:append  = " file://hpm.json"
SRC_URI:append  = " file://blacklist.json"
SRC_URI:append  = " file://is162f22.json"  

do_install:append() {
    rm -f ${D}/usr/share/entity-manager/configurations/*.json
    install -d ${D}${datadir}/entity-manager
	
	install -m 0644 -D ${WORKDIR}/git/configurations/intel_front_panel.json \
        ${D}${datadir}/entity-manager/configurations/intel_front_panel.json
	install -m 0644 -D ${WORKDIR}/is162f22.json \
		${D}${datadir}/entity-manager/configurations/is162f22.json
    install -m 0644 -D ${WORKDIR}/scm.json \
        ${D}${datadir}/entity-manager/configurations/scm.json
    install -m 0644 -D ${WORKDIR}/hpm.json \
        ${D}${datadir}/entity-manager/configurations/hpm.json
    install -m 0644 ${WORKDIR}/blacklist.json \
        ${D}${datadir}/entity-manager/blacklist.json
}
