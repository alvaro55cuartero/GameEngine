package engine.platform.vulkan;

import static org.lwjgl.system.MemoryUtil.memAllocPointer;
import static org.lwjgl.system.MemoryUtil.memUTF8;
import static org.lwjgl.vulkan.EXTDebugReport.VK_EXT_DEBUG_REPORT_EXTENSION_NAME;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.vulkan.EXTDebugReport.*;
import static org.lwjgl.vulkan.KHRSwapchain.*;
import static org.lwjgl.vulkan.KHRSurface.*;
import static org.lwjgl.vulkan.VK10.*;
import static org.lwjgl.demo.vulkan.VKUtil.*;
import static org.lwjgl.vulkan.
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWVulkan.*;

import java.nio.ByteBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.vulkan.VK10;
import org.lwjgl.vulkan.VkApplicationInfo;
import org.lwjgl.vulkan.VkInstance;
import org.lwjgl.vulkan.VkInstanceCreateInfo;

public class Test {

	VkInstance instance;

	public void createInstance(PointerBuffer requiredExtensions) {
		VkApplicationInfo appInfo = VkApplicationInfo.calloc().sType(VK10.VK_STRUCTURE_TYPE_APPLICATION_INFO)
				.apiVersion(VK10.VK_API_VERSION_1_0);

		PointerBuffer ppEnabledExtensionNames = memAllocPointer(requiredExtensions.remaining() + 1);
		ppEnabledExtensionNames.put(requiredExtensions);
		ByteBuffer VK_EXT_DEBUG_REPORT_EXTENSION = memUTF8(VK_EXT_DEBUG_REPORT_EXTENSION_NAME);
		ppEnabledExtensionNames.put(VK_EXT_DEBUG_REPORT_EXTENSION);
		ppEnabledExtensionNames.flip();
		PointerBuffer ppEnabledLayerNames = memAllocPointer(layers.length);

		VkInstanceCreateInfo pCreateInfo = VkInstanceCreateInfo.calloc()
				.sType(VK10.VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO).pApplicationInfo(appInfo);

	}

}
