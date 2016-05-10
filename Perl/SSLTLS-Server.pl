use strict;
use warnings;

use IO::Socket::SSL;

# Author Nazrul 2014768869

# auto-flush on socket
$| = 1;

# creating a listening socket
my $socket = new IO::Socket::SSL (
    LocalHost => '0.0.0.0',
    LocalPort => '4433',
    Listen => 5,
    Reuse => 1,
    SSL_cert_file => 'naz.crt',
    SSL_key_file => 'naz.key',
);
die "cannot create socket $!\n" unless $socket;
print "Begin Assignment2 in Perl by Nazrul\nServer waiting for client connection on port 4433\n";

# waiting for a new client connection
my $client = $socket->accept() or die "SSL_ERROR = $SSL_ERROR\n";

# get information about a newly connected client
my $client_address = $client->peerhost();
my $client_port = $client->peerport();
print "New connection from $client_address:$client_port\n";

# read from the connected client
my $data = "";
$client->read($data);
print "Received message: $data";

print "End\n";
shutdown($client, 1);
