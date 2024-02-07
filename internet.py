import wifi
import os
import ipaddress
import socketpool

class Internet:
    # variables

    ip_address = None
    ssid = None
    password = None

    def __init__(self) -> None:
        pass

    def connect(self) -> None:
        ''' Connects to wifi network configured in settings.toml adds IP address to variable in object '''

        self.ssid, self.password = os.getenv("WIFI_SSID"), os.getenv("WIFI_PASS")

        wifi.radio.connect(ssid = self.ssid, password = self.password)
        self.ip_address = wifi.radio.ipv4_address

    def ping(self) -> float:
        ''' Ping cloudflare to test connectivity '''
        return wifi.radio.ping(ip = ipaddress.IPv4Address('1.1.1.1'))
    
class HttpServer:
    socketpool = None
    socket = None


    def __init__(self, port: int) -> None:
        ''' Create structure for HTTP server '''

        # create socketpool and socket
        self.socket_pool = socketpool.SocketPool(wifi.radio)
        self.socket = self.socket_pool.socket(socketpool.SocketPool.AF_INET, socketpool.SocketPool.SOCK_STREAM)
        self.socket.settimeout(1)
        # bind socket to address
        self.socket.bind(('0.0.0.0', port))
        
        # listen with socket
        self.socket.listen(1)
    
    def server_thread(self, source: function) -> None:
        ''' Main thread for http server which takes a function as input returns the return value of said function
        upon receiving a request '''
        # wait for connections and accept with connection object
        try:
            connection, client_addr = self.socket.accept()

            # create empty byte area with 256 bytes as buffer
            array = bytearray(265)

            # save into array
            connection.recv_into(array, 256)
            print(f'New Connection --> {client_addr[0]}')
            
            # reply to request with a string
            connection.sendall(f'HTTP/1.0 200 OK\n\n{source()}'.encode("utf-8"))

            
            # close connection to accept next one
            connection.close()
        except OSError:
            pass