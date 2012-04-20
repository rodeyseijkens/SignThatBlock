package nl.rodey.SignThatBlock;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class SignThatBlockPlayerListener implements Listener 
{
	private Logger log = Logger.getLogger("Minecraft");
	private static SignThatBlockMain plugin;

	public SignThatBlockPlayerListener(SignThatBlockMain instance) 
	{
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) 
	{
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
        if (block == null) return; 
        
        if(plugin.debug)
		{ 
			log.info("["+plugin.getDescription().getName()+"] Block clicked!");
		}
        
        String[] definedBlocksArray = plugin.definedBlocks.split(",");
		
		// Attach a SIGN_POST to a BED with a RightClick
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(plugin.debug)
			{ 
				log.info("["+plugin.getDescription().getName()+"] Block is Right Clicked!");
			}
			
			if(block.getType().equals(Material.SIGN_POST))
			{
				if(player.hasPermission("signthatblock.attach"))
				{
					if(plugin.debug)
					{ 
						log.info("["+plugin.getDescription().getName()+"] Clicked block is sign post");
					}
					
					for ( String defBlock : definedBlocksArray)
					{
						if(plugin.debug)
						{ 
							log.info("["+plugin.getDescription().getName()+"] Defined Block: "+defBlock);
						}
						
						if (block.getRelative(BlockFace.NORTH).getTypeId() == Integer.parseInt(defBlock)) 
						{
		
							Sign signBlock = (Sign) block.getState();
							String[] signText = signBlock.getLines(); //Get Lines
				            
							block.setTypeId(68); // Change to Wall Sign
							block.setData((byte) 0x5); // Set Direction
		
							signBlock = (Sign) block.getState(); // Get New State
							
							int i = 0; // Set Line Count
							for ( String lineText : signText)
							{
								signBlock.setLine(i, lineText); // Write Line
		
								if(plugin.debug)
								{ 
									log.info("["+plugin.getDescription().getName()+"] Line "+i+": "+lineText);
								}
								
								i++; // Update Line Count
								
								signBlock.update(); // Save Sign
							}
							
							return;
						}
						else if (block.getRelative(BlockFace.EAST).getTypeId() == Integer.parseInt(defBlock)) 
						{
		
							Sign signBlock = (Sign) block.getState();
							String[] signText = signBlock.getLines(); //Get Lines
				            
							block.setTypeId(68); // Change to Wall Sign
							block.setData((byte) 0x3); // Set Direction
		
							signBlock = (Sign) block.getState(); // Get New State
							
							int i = 0; // Set Line Count
							for ( String lineText : signText)
							{
								signBlock.setLine(i, lineText); // Write Line
		
								if(plugin.debug)
								{ 
									log.info("["+plugin.getDescription().getName()+"] Line "+i+": "+lineText);
								}
								
								i++; // Update Line Count
								
								signBlock.update(); // Save Sign
							}
							
							return;
						}
						else if (block.getRelative(BlockFace.SOUTH).getTypeId() == Integer.parseInt(defBlock)) 
						{
		
							Sign signBlock = (Sign) block.getState();
							String[] signText = signBlock.getLines(); //Get Lines
				            
							block.setTypeId(68); // Change to Wall Sign
							block.setData((byte) 0x4); // Set Direction
		
							signBlock = (Sign) block.getState(); // Get New State
							
							int i = 0; // Set Line Count
							for ( String lineText : signText)
							{
								signBlock.setLine(i, lineText); // Write Line
		
								if(plugin.debug)
								{ 
									log.info("["+plugin.getDescription().getName()+"] Line "+i+": "+lineText);
								}
								
								i++; // Update Line Count
								
								signBlock.update(); // Save Sign
							}
							
							return;
						}
						else if (block.getRelative(BlockFace.WEST).getTypeId() == Integer.parseInt(defBlock)) 
						{
		
							Sign signBlock = (Sign) block.getState();
							String[] signText = signBlock.getLines(); //Get Lines
				            
							block.setTypeId(68); // Change to Wall Sign
							block.setData((byte) 0x2); // Set Direction
		
							signBlock = (Sign) block.getState(); // Get New State
							
							int i = 0; // Set Line Count
							for ( String lineText : signText)
							{
								signBlock.setLine(i, lineText); // Write Line
		
								if(plugin.debug)
								{ 
									log.info("["+plugin.getDescription().getName()+"] Line "+i+": "+lineText);
								}
								
								i++; // Update Line Count
								
								signBlock.update(); // Save Sign
							}
							
							return;
						}
					}
				}
			}
		}
	}
}