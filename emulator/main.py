import bluetooth

uuid = "00001101-0000-1000-8000-00805f9b34fb"

sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
port = 1000

sock.bind(("", bluetooth.PORT_ANY))
sock.listen(1)

bluetooth.advertise_service(sock, 
        "OBDService",
        service_id = uuid,
        service_classes = [uuid, bluetooth.SERIAL_PORT_CLASS],
        profiles = [ bluetooth.SERIAL_PORT_PROFILE ]
)

conn, address = sock.accept()
print("Accepted connection")

while True:
    data = conn.recv(1024)
    print("Received data {}".format(data))


