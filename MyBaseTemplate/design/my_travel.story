<?Infragistics.Models format="xaml" version="2"?>
<Flow xmlns="http://prototypes.infragistics.com/flows"
                                                         xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml">
    <Flow.Items>
        <Step x:Uid="$1" ContentView="/launcher_screen.screen" ContentState="ec539994-4975-4bee-a205-b6a24cf39e5b" X="100" Y="100" Width="430" Height="322" />
        <Step x:Uid="$2" X="580" Y="100" Width="430" Height="322" />
        <Connector Source="{Reference $1}" Target="{Reference $2}" />
    </Flow.Items>
</Flow>
